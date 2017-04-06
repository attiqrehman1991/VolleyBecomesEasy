/*
 *
 *  * MIT License
 *  *
 *  * Copyright (c) 2017 Attiq ur Rehman
 *  *
 *  * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  * of this software and associated documentation files (the "Software"), to deal
 *  * in the Software without restriction, including without limitation the rights
 *  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  * copies of the Software, and to permit persons to whom the Software is
 *  * furnished to do so, subject to the following conditions:
 *  *
 *  * The above copyright notice and this permission notice shall be included in all
 *  * copies or substantial portions of the Software.
 *  *
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  * SOFTWARE.
 *
 *
 */

package com.systems.network_manager.preferences;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @author Attiq ur Rehman
 *         created on 4/10/2016.
 *         attiq.ur.rehman1991@gmail.com
 *
 *         Added functions are
 *         writeBoolean -- to write boolean preferences
 *         readBoolean  -- to read boolean preferences
 *         writeInteger -- to write integer preferences
 *         readInteger  -- to read integer preferences
 *         writeString  -- to write string preferences
 *         readString   -- to read string preferences
 *         writeFloat   -- to write float preferences
 *         readFloat    -- to read float preferences
 *         writeLong    -- to write long preferences
 *         readLong     -- to read long preferences
 *         getPreferences - get preference instance
 *         getEditor	  - editor instance to edit preferences
 */

public class InstructionPreferences {
    public static final int MODE = Context.MODE_PRIVATE;

    public static final String APP_NAME = "TEST_PROJECT";
	public static final String PREF_NAME = APP_NAME + "INSTRUCT_PREF";

	public static void writeBoolean(Context context, String key, boolean value) {
		if(context == null) {
			return;
		}
		Editor editor = getEditor(context);
		if(editor == null) {
			return;
		}
		editor.putBoolean(key, value).commit();
	}

	public static boolean readBoolean(Context context, String key, boolean defValue) {
		if(context == null) {
			return defValue;
		}
		SharedPreferences preferences = getPreferences(context);
		if(preferences == null) {
			return defValue;
		}
		return preferences.getBoolean(key, defValue);
	}

	public static void writeInteger(Context context, String key, int value) {
		if(context == null) {
			return;
		}
		Editor editor = getEditor(context);
		if(editor == null) {
			return;
		}
			editor.putInt(key, value).commit();
	}

	public static int readInteger(Context context, String key, int defValue) {
		if(context == null) {
			return defValue;
		}
		SharedPreferences preferences = getPreferences(context);
		if(preferences == null) {
			return defValue;
		}
		return preferences.getInt(key, defValue);
	}

	public static void writeString(Context context, String key, String value) {
		if(context == null) {
			return;
		}
		Editor editor = getEditor(context);
		if(editor == null) {
			return;
		}
			editor.putString(key, value).commit();
	}

	public static String readString(Context context, String key, String defValue) {
		if(context == null) {
			return defValue;
		}
		SharedPreferences preferences = getPreferences(context);
		if(preferences == null) {
			return defValue;
		}
		return preferences.getString(key, defValue);
	}

	public static void writeFloat(Context context, String key, float value) {
		if(context == null) {
			return;
		}
		Editor editor = getEditor(context);
		if(editor == null) {
			return;
		}
			editor.putFloat(key, value).commit();
	}

	public static float readFloat(Context context, String key, float defValue) {
		if(context == null) {
			return defValue;
		}
		SharedPreferences preferences = getPreferences(context);
		if(preferences == null) {
			return defValue;
		}
		return preferences.getFloat(key, defValue);
	}

	public static void writeLong(Context context, String key, long value) {
		if(context == null) {
			return;
		}
		Editor editor = getEditor(context);
		if(editor == null) {
			return;
		}
			editor.putLong(key, value).commit();
	}

	public static long readLong(Context context, String key, long defValue) {
		if(context == null) {
			return defValue;
		}
		SharedPreferences preferences = getPreferences(context);
		if(preferences == null) {
			return defValue;
		}
		return preferences.getLong(key, defValue);
	}

	public static SharedPreferences getPreferences(Context context) {
		if(context == null) {
			return null;
		}
		return context.getSharedPreferences(PREF_NAME, MODE);
	}

	public static Editor getEditor(Context context) {
		if(context == null) {
			return null;
		}
		SharedPreferences preferences = getPreferences(context);
		if(preferences == null) {
			return null;
		}
		return preferences.edit();
	}
}

