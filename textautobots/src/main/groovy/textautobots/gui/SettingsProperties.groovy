package textautobots.gui

class SettingsProperties extends Properties {
    final String propertiesFilePath = "settings.properties";

    public void loadSettingsProperties() {
        try {
            FileInputStream stream = new FileInputStream(propertiesFilePath)
            load(stream);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Could not load font settings. Using default settings.");
        }
    }

     public void saveSettingsProperties() {
         try (FileOutputStream out = new FileOutputStream(propertiesFilePath)) {
             store(out, "Settings");
         } catch (IOException e) {
             System.out.println("Failed to save font settings.");
         }
    }
}
