<p align="center"><br><img src="https://user-images.githubusercontent.com/236501/85893648-1c92e880-b7a8-11ea-926d-95355b8175c7.png" width="128" height="128" /></p>
<h3 align="center">MDM App Config</h3>
<p align="center"><strong><code>@capacitor-community/mdm-appconfig</code></strong></p>
<p align="center">
  Capacitor community plugin for reading app configurations written by a MDM (see <a href="https://www.appconfig.org/">appconfig.org</a>) such as VMWare Workspace One.
</p>

## Maintainers

| Maintainer | GitHub | Social |
| -----------| -------| -------|
| Damian Tarnawsky | [dtarnawsky](https://github.com/dtarnawsky) | [@damiantarnawsky](https://twitter.com/damiantarnawsky) |

## Versions

| Plugin | Capacitor | Documentation                                                                     |
| ------ | --------- | --------------------------------------------------------------------------------- |
| 4.x    | 4.x       | [README](https://github.com/capacitor-community/mdm-appconfig/blob/main/README.md) |

## Installation

```bash
npm install @capacitor-community/mdm-appconfig
npx cap sync
```
## Configuration

### Android

Add the following line to your `androidmanifest.xml` (under `<application...>`):
```xml
<meta-data android:name=“android.content.APP_RESTRICTIONS” android:resource=“@xml/app_restrictions” />
```

Create an XML file named `app_restrictions.xml` in the `res/xml` directory to house your defined app restrictions, the format for the XML can be found [here](http://developer.android.com/reference/android/content/RestrictionsManager.html).

Here is an example `app_restrictions.xml` that defines 3 strings:
```xml
<?xml version="1.0" encoding="utf-8"?>
<restrictions xmlns:android="http://schemas.android.com/apk/res/android">
	<restriction android:key="ionic.email" android:title="email" android:restrictionType="string" android:defaultValue="" />
	<restriction android:key="ionic.user" android:title="user" android:restrictionType="string" android:defaultValue="" />
	<restriction android:key="ionic.userid" android:title="userid" android:restrictionType="string" android:defaultValue="" />
</restrictions>
```

## Usage
```typescript
import { AppConfig } from '@capacitor-community/mdm-appconfig';

// Get a value:
const result = await AppConfig.getValue({ key: 'my.variable.name' });
console.log(result.value);
```

## VMWare Workspace 1
When distributing an Application you can create an Assignment and under **Application Configuration** you can send a set of configuration keys that can be read by your application. The screenshot belows shows keys called `ionic.email`, `ionic.user` and `ionic.userid` which Workspace 1 has will write with values related to the enrolled user.
![ws1-screenshot](https://user-images.githubusercontent.com/84595830/214071169-3d7f39e9-aa8c-4b8c-8e43-3a072786543c.png)

## API

<docgen-index>

* [`getValue(...)`](#getvalue)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### getValue(...)

```typescript
getValue(options: getValueOptions) => Promise<GetValueResult>
```

Get a value from App Config. On iOS, values are obtained from UserDefaults in com.apple.configuration.managed.

| Param         | Type                                                        | Description          |
| ------------- | ----------------------------------------------------------- | -------------------- |
| **`options`** | <code><a href="#getvalueoptions">getValueOptions</a></code> | Options to get value |

**Returns:** <code>Promise&lt;<a href="#getvalueresult">GetValueResult</a>&gt;</code>

**Since:** 1.0.0

--------------------


### Interfaces


#### GetValueResult

| Prop        | Type                | Description | Since |
| ----------- | ------------------- | ----------- | ----- |
| **`value`** | <code>string</code> | The value   | 1.0.0 |


#### getValueOptions

| Prop      | Type                | Description                | Since |
| --------- | ------------------- | -------------------------- | ----- |
| **`key`** | <code>string</code> | The key (or variable name) | 1.0.0 |

</docgen-api>
