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
            Set<String> keys = restrictions.keySet();
            final String keyName = call.getString("key");
            if (keys != null) {
                for (String key : keys) {
                    if (key.equals(keyName)) {
                        JSObject ret = new JSObject();
                        String value = restrictions.getString(key);
                        ret.put("value", value == null ? JSObject.NULL : value);
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
            call.error(ex.getLocalizedMessage());
        }
    }
}
