/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.cardreader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareUltralight;

import com.example.android.common.logger.Log;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Callback class, invoked when an NFC card is scanned while the device is running in reader mode.
 *
 * Reader mode can be invoked by calling NfcAdapter
 */
public class LoyaltyCardReader implements NfcAdapter.ReaderCallback {
    public static MifareUltralight mfDep =null;

    private static final String TAG = "LoyaltyCardReader";
    private static final int  PAGE_FIRSTNAME = 4;
    private static final int  PAGE_LASTNAME = 8;
    private static final int  PAGE_EMAIL = 12;
    private static final int  PAGE_MOBILE = 18;
    private static final int  PAGE_BAL = 22;

    // AID for our loyalty card service.
    private static final String SAMPLE_LOYALTY_CARD_AID = "F222222222";
    // ISO-DEP command HEADER for selecting an AID.
    // Format: [Class | Instruction | Parameter 1 | Parameter 2]
    private static final String SELECT_APDU_HEADER = "00A40400";
    // "OK" status word sent in response to SELECT AID command (0x9000)
    private static final byte[] SELECT_OK_SW = {(byte) 0x90, (byte) 0x00};

    // Weak reference to prevent retain loop. mAccountCallback is responsible for exiting
    // foreground mode before it becomes invalid (e.g. during onPause() or onStop()).
    private WeakReference<AccountCallback> mAccountCallback;

    public interface AccountCallback {
        public void onAccountReceived(String account);
    }

    public LoyaltyCardReader(AccountCallback accountCallback) {
        mAccountCallback = new WeakReference<AccountCallback>(accountCallback);
    }

    /**
     * Callback when a new tag is discovered by the system.
     *
     * <p>Communication with the card should take place here.
     *
     * @param tag Discovered tag
     */
    @Override
    public void onTagDiscovered(Tag tag) {
        Log.i(TAG, "New tag discovered");
        // Android's Host-based Card Emulation (HCE) feature implements the ISO-DEP (ISO 14443-4)
        // protocol.
        //
        // In order to communicate with a device using HCE, the discovered tag should be processed
        // using the IsoDep class.
 /*       IsoDep isoDep = IsoDep.get(tag);
        if (isoDep != null) {
            try {
                // Connect to the remote NFC device
                isoDep.connect();
                // Build SELECT AID command for our loyalty card service.
                // This command tells the remote device which service we wish to communicate with.
                Log.i(TAG, "Requesting remote AID: " + SAMPLE_LOYALTY_CARD_AID);
                byte[] command = BuildSelectApdu(SAMPLE_LOYALTY_CARD_AID);
                // Send command to remote device
                Log.i(TAG, "Sending: " + ByteArrayToHexString(command));
                byte[] result = isoDep.transceive(command);
                // If AID is successfully selected, 0x9000 is returned as the status word (last 2
                // bytes of the result) by convention. Everything before the status word is
                // optional payload, which is used here to hold the account number.
                int resultLength = result.length;
                byte[] statusWord = {result[resultLength-2], result[resultLength-1]};
                byte[] payload = Arrays.copyOf(result, resultLength-2);
                if (Arrays.equals(SELECT_OK_SW, statusWord)) {
                    // The remote NFC device will immediately respond with its stored account number
                    String accountNumber = new String(payload, "UTF-8");
                    Log.i(TAG, "Received: " + accountNumber);
                    // Inform CardReaderFragment of received account number
                    mAccountCallback.get().onAccountReceived(accountNumber);
                }
            } catch (IOException e) {
                Log.e(TAG, "Error communicating with card: " + e.toString());
            }
        }

*/      int nextstep = cardInfo.getInstance().getNextStep() ;

        mfDep = MifareUltralight.get(tag);
        if (mfDep != null) {
            try {
                // Connect to the remote NFC device
                mfDep.connect();

                if(nextstep == cardInfo.step_action.STEP_PURCHASE.ordinal()) {
                    byte[] payload = mfDep.readPages(PAGE_BAL); // 16bytes
                    long bal = 0;
                    try {
                        bal = Long.parseLong(ByteArrayToAscii(payload));
                    } catch (NumberFormatException nfe) {
                        bal = 0;
                    }
                    bal = bal - cardInfo.getInstance().getDeposit();
                    if(bal < 0) {
                        bal = 0;// TODO
                    }
                    writeTag(PAGE_BAL,String.format("%-16s",String.format("%016d",bal)));
                    cardInfo.getInstance().setBal(bal);
                }

                if(nextstep == cardInfo.step_action.STEP_DEPOSIT.ordinal()||
                   nextstep == cardInfo.step_action.STEP_TAPCARD_WRITE_CUST_DEPOSIT.ordinal()) {
                    byte[] payload = mfDep.readPages(PAGE_BAL); // 16bytes
                    long bal = 0;
                    try {
                        bal = Long.parseLong(ByteArrayToAscii(payload));
                    } catch (NumberFormatException nfe) {
                        bal = 0;
                    }
                    bal = bal + cardInfo.getInstance().getDeposit();
                    Log.i(TAG, "readpages :" + ByteArrayToAscii(payload));
                    writeTag(PAGE_BAL,String.format("%-16s",String.format("%016d",bal)));
                    Log.i(TAG, "writepages :" + String.format("%016d",bal));
                    cardInfo.getInstance().setBal(bal);
                }

                if(nextstep == cardInfo.step_action.STEP_TAPCARD_WRITE_CUST.ordinal()||
                   nextstep == cardInfo.step_action.STEP_TAPCARD_WRITE_CUST_DEPOSIT.ordinal()) {
                    writeTag(PAGE_FIRSTNAME,String.format("%-16s",cardInfo.getInstance().getFirstname()));
                    writeTag(PAGE_LASTNAME,String.format("%-16s",cardInfo.getInstance().getLastname()));
                    writeTag(PAGE_EMAIL,String.format("%-16s",cardInfo.getInstance().getEmail()));
                    writeTag(PAGE_MOBILE,String.format("%-16s",cardInfo.getInstance().getMobile()));
                    Log.i(TAG, "writepages done" );
                }

                if(nextstep == cardInfo.step_action.STEP_TAPCARD_READ.ordinal())
                {
                    byte[] payload = mfDep.readPages(PAGE_FIRSTNAME); // 16bytes
                    Log.i(TAG, "pages: " + ByteArrayToHexString(payload));
                }


            } catch (IOException e) {
                Log.e(TAG, "Error communicating with card: " + e.toString());
            } finally {
                if (mfDep != null) {
                    try {
                        mfDep.close();
                    }
                    catch (IOException e) {
                        Log.e(TAG, "Error closing tag...", e);
                    }
                }
            }
        }
        String accountNumber = "98654321";
        mAccountCallback.get().onAccountReceived(accountNumber);

    }

    /**
     * Build APDU for SELECT AID command. This command indicates which service a reader is
     * interested in communicating with. See ISO 7816-4.
     *
     * @param aid Application ID (AID) to select
     * @return APDU for SELECT AID command
     */
    public static byte[] BuildSelectApdu(String aid) {
        // Format: [CLASS | INSTRUCTION | PARAMETER 1 | PARAMETER 2 | LENGTH | DATA]
        return HexStringToByteArray(SELECT_APDU_HEADER + String.format("%02X", aid.length() / 2) + aid);
    }

    /**
     * Utility class to convert a byte array to a hexadecimal string.
     *
     * @param bytes Bytes to convert
     * @return String, containing hexadecimal representation.
     */
    public static String ByteArrayToHexString(byte[] bytes) {
        final char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for ( int j = 0; j < bytes.length; j++ ) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    /**
     * Utility class to convert a hexadecimal string to a byte string.
     *
     * <p>Behavior with input strings containing non-hexadecimal characters is undefined.
     *
     * @param s String containing hexadecimal characters to convert
     * @return Byte array generated from input
     */
    public static byte[] HexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static String ByteArrayToAscii(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length);
        for (int i = 0; i < data.length; ++ i) {
            if (data[i] < 0) throw new IllegalArgumentException();
            sb.append((char) data[i]);
        }
        return sb.toString();
    }

    public void writeTag( int pageoffset, String tagText) {
        //MifareUltralight ultralight = MifareUltralight.get(tag);
        try {
            //ultralight.connect();
            int idx = 0;
            mfDep.writePage(pageoffset, tagText.substring(idx,idx+4).getBytes(Charset.forName("US-ASCII")));
            idx = idx + 4;
            mfDep.writePage(pageoffset+1, tagText.substring(idx,idx+4).getBytes(Charset.forName("US-ASCII")));
            idx = idx + 4;
            mfDep.writePage(pageoffset+2, tagText.substring(idx,idx+4).getBytes(Charset.forName("US-ASCII")));
            idx = idx + 4;
            mfDep.writePage(pageoffset+3, tagText.substring(idx,idx+4).getBytes(Charset.forName("US-ASCII")));
        } catch (IOException e) {
            Log.e(TAG, "IOException while closing MifareUltralight...", e);
        } finally {
            //try {
                //ultralight.close();
            //} catch (IOException e) {
              //  Log.e(TAG, "IOException while closing MifareUltralight...", e);
            //}
        }
    }

    public String readTag(Tag tag) {
        MifareUltralight mifare = MifareUltralight.get(tag);
        try {
            mifare.connect();
            byte[] payload = mifare.readPages(4);
            return new String(payload, Charset.forName("US-ASCII"));
        } catch (IOException e) {
            Log.e(TAG, "IOException while writing MifareUltralight message...", e);
        } finally {
            if (mifare != null) {
                try {
                    mifare.close();
                }
                catch (IOException e) {
                    Log.e(TAG, "Error closing tag...", e);
                }
            }
        }
        return null;
    }
}
