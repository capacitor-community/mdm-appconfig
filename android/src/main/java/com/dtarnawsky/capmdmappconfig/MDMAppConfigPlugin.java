package com.dtarnawsky.capmdmappconfig;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.RestrictionsManager;
import android.os.Bundle;
import android.view.WindowManager;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@CapacitorPlugin(name = "MDMAppConfig")
public class MDMAppConfigPlugin extends Plugin {

    @PluginMethod
    public void getValue(PluginCall call) {
        try {
            Context context = this.bridge.getActivity().getApplicationContext();
            RestrictionsManager resManager = (RestrictionsManager) context.getSystemService(Context.RESTRICTIONS_SERVICE);
            Bundle restrictions = resManager.getApplicationRestrictions();
            if (restrictions == null) {
                call.reject("No configurations found");
                return;
            }
            Set<String> keys = restrictions.keySet();
            final String keyName = call.getString("key");
            if (keys != null) {
                for (String key : keys) {
                    if (key.equals(keyName)) {
                        JSObject ret = new JSObject();
                        Object value = restrictions.get(key); // handle any type
                        Object converted = convertBundleValue(value);
                        ret.put("value", converted == null ? JSObject.NULL : converted);
                        call.resolve(ret);
                        return;
                    }
                }
            } else {
                call.reject("No configurations found");
                return;
            }
            call.reject("key " + keyName + " cannot be found");
        } catch (Exception ex) {
            call.reject(ex.getLocalizedMessage());
        }
    }

    private Object convertBundleValue(Object value) throws JSONException {
        if (value == null) {
            return null;
        }

        // Bundle -> JSObject (nested object)
        if (value instanceof Bundle) {
            Bundle b = (Bundle) value;
            JSObject obj = new JSObject();
            for (String k : b.keySet()) {
                Object v = b.get(k);
                Object conv = convertBundleValue(v);
                obj.put(k, conv == null ? JSObject.NULL : conv);
            }
            return obj;
        }
        if (value instanceof Bundle[]) {
            JSONArray arr = new JSONArray();
            for (Bundle bb : (Bundle[]) value) {
                Object conv = convertBundleValue(bb);
                arr.put(conv == null ? JSONObject.NULL : conv);
            }
            return arr;
        }
        if (value instanceof String[]) {
            JSONArray arr = new JSONArray();
            for (String s : (String[]) value) {
                arr.put(s == null ? JSONObject.NULL : s);
            }
            return arr;
        }
        if (value instanceof CharSequence[]) {
            JSONArray arr = new JSONArray();
            for (CharSequence cs : (CharSequence[]) value) {
                arr.put(cs == null ? JSONObject.NULL : cs.toString());
            }
            return arr;
        }
        if (value instanceof java.util.List) {
            JSONArray arr = new JSONArray();
            for (Object o : (java.util.List<?>) value) {
                Object conv = convertBundleValue(o);
            }
            if (value instanceof boolean[]) {
                JSONArray arr = new JSONArray();
                for (boolean b : (boolean[]) value) {
                    arr.put(b);
                }
                return arr;
            }
            if (value instanceof int[]) {
                JSONArray arr = new JSONArray();
                for (int i : (int[]) value) {
                    arr.put(i);
                }
                return arr;
            }
            if (value instanceof long[]) {
                JSONArray arr = new JSONArray();
                for (long l : (long[]) value) {
                    arr.put(l);
                }
                return arr;
            }
            if (value instanceof double[]) {
                JSONArray arr = new JSONArray();
                for (double d : (double[]) value) {
                    arr.put(d);
                }
                return arr;
            }

            if (value instanceof Object[]) {
                JSONArray arr = new JSONArray();
                for (Object o : (Object[]) value) {
                    Object conv = convertBundleValue(o);
                    arr.put(conv == null ? JSONObject.NULL : conv);
                }
                return arr;
            }
            return value.toString();
        }
    }
