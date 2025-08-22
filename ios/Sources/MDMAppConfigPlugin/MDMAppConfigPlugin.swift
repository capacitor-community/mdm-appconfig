import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(MDMAppConfigPlugin)
public class MDMAppConfigPlugin: CAPPlugin, CAPBridgedPlugin {
    public let identifier = "MDMAppConfigPlugin" 
    public let jsName = "MDMAppConfig" 
    public let pluginMethods: [CAPPluginMethod] = [
        CAPPluginMethod(name: "getValue", returnType: CAPPluginReturnPromise),
    ] 

    @objc func getValue(_ call: CAPPluginCall) {
        let key = call.getString("key")
        guard let key = key else {
            call.reject("Parameter `key` not provided.")
            return
        }
        if let managedConfigDict = UserDefaults.standard.dictionary(forKey: "com.apple.configuration.managed") {
            if let keyValue = managedConfigDict[key] {
                call.resolve([ "value": keyValue ])
            } else {
                call.reject("Key not found.")
            }
        } else {
            call.reject("Managed configuration not found.")
        }
    }
}
