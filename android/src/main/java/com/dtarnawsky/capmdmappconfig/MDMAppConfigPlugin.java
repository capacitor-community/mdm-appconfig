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

@CapacitorPlugin(name = "MDMAppconfig")
public class MDMAppConfigPlugin extends Plugin {

    @PluginMethod
    public void getValue(PluginCall call) {
        JSObject ret = new JSObject();
        try {
            Context context = this.bridge.getActivity().getApplicationContext();
            RestrictionsManager resManager = (RestrictionsManager) context.getSystemService(Context.RESTRICTIONS_SERVICE);
            Bundle restrictions = resManager.getApplicationRestrictions();
            Set<String> keys = restrictions.keySet();
            String keyName = call.getString("key");
            if (keys != null) {
                for (String key : keys) {
                    if (key == keyName) {
                        call.resolve(
                            new JSObject() {
                                {
                                    put("value", JSONObject.wrap(restrictions.get(key)));
                                }
                            }
                        );
                        return;
                    }
                }
            } else {
                call.reject("No configurations found");
                return;
            }
            call.reject("key cannot be found");
        } catch (Exception ex) {
            call.error(ex.getLocalizedMessage());
        }
    }
}
