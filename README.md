# map_platform_views

### **1. Go to Google Cloud Console**
Visit: [https://console.cloud.google.com/](https://console.cloud.google.com/)

---

### **2. Create a New Project (or Select Existing)**

**If you don't have a project:**
1. Click the **project dropdown** at the top (next to "Google Cloud")
2. Click **"NEW PROJECT"**
3. Enter project name: `MyFlutterMapsApp`
4. Click **"CREATE"**
5. Wait a few seconds, then select your new project

---

### **3. Enable Maps SDK for Android**

1. In the search bar at the top, type: **"Maps SDK for Android"**
2. Click on **"Maps SDK for Android"**
3. Click the blue **"ENABLE"** button
4. Wait for it to enable (~10 seconds)

---

### **4. Create API Key**

1. Go to **"Credentials"** (left sidebar) or search "Credentials"
2. Click **"+ CREATE CREDENTIALS"** at the top
3. Select **"API key"**
4. A popup will show your API key → **Copy it!**
   ```
   AIzaSyC1234567890abcdefghijklmnop-Example
   ```
5. in Android manifest.xml add the key

```
    <!-- Add your own Google Maps API key here. -->
    <meta-data android:name="com.google.android.geo.API_KEY" 
    android:value="ADD_YOUR_GOOGLE_MAPS_API_KEY_HERE" />
```
