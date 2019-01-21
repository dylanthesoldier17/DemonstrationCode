package com.dylanthesoldier.apprepofx.model.pkg;

import com.dylanthesoldier.apprepofx.controllers.settings.AppSettings;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Created by c-dmoore on 10/20/2016.
 *
 * Singleton
 */
public class PackageLoader {

    private static PackageLoader pl = null;

    private String location = AppSettings.PACKAGES_DIRECTORY;
    // --- Constructor ---
    protected PackageLoader() throws IOException {}

    public static PackageLoader createInstance() throws IOException {
        if (pl == null)
            pl = new PackageLoader();

        return pl;
    }

    public String getLocation() {
        return location;
    }

    public PackageLoader setLocation(String location) {
        this.location = location;
        return this;
    }

    // --- XML Properties Export / Import ---
    public void loadXML(ObservableList<Package> items) throws IOException {
        // get a list of files with .pkg extension
        // then for each package path load the corresponding file to a package instance
        // finally add to the package ObservableList
        Files.list(Paths.get(location))
                .filter(f -> f.toString().contains(".pkg"))
                .collect(Collectors.toList()).forEach(i ->{
            Package pkg = null;
            pkg = loadPackageXML(i.toAbsolutePath().toString());
            if (pkg != null)
                items.add(pkg);
                }
        );
    }
    public Package loadPackageXML(String package_path){
        try (FileInputStream fis = new FileInputStream(package_path)){
            Properties prop = new Properties();
            prop.loadFromXML(fis);
            Package pkg = new Package();
            pkg.setName(prop.getProperty("Name"));
            pkg.setVersion(prop.getProperty("Version", ""));
            pkg.setPackageName(prop.getProperty("PackageName"));
            pkg.setPackageURL(prop.getProperty("PackageURL", ""));
            pkg.setSHA_Hash(prop.getProperty("SHA_Hash", ""));
            pkg.setSilentUninstall(prop.getProperty("SilentUninstall", ""));
            pkg.setSilentInstall(prop.getProperty("SilentInstall", ""));
            pkg.setSizeInBytes(prop.getProperty("SizeInBytes"));
            pkg.setWebsite(prop.getProperty("Website", ""));
            pkg.setUniqueID(Integer.parseInt(prop.getProperty("UniqueID")));
            pkg.setDescription(prop.getProperty("Description", ""));
            return pkg;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    public String exportXML(Package... packages) {
        String file = "";
        for(Package p: packages) {
            file = location + "/" + p.getPkgFileName();
            try (FileOutputStream fos = new FileOutputStream(file)) {
                Properties prop = new Properties();
                prop.put("Name", p.getName());
                prop.put("Version", p.getVersion());
                prop.put("PackageName", p.getPackageName());
                prop.put("PackageURL", p.getPackageURL());
                prop.put("SHA_Hash", p.getSHA_Hash());
                prop.put("SilentInstall", p.getSilentInstall());
                prop.put("SilentUninstall", p.getSilentUninstall());
                prop.put("SizeInBytes", p.getSizeInBytes());
                prop.put("Website", p.getWebsite());
                prop.put("UniqueID", p.getUniqueID().toString());
                prop.put("Description", p.getDescription());
                prop.storeToXML(fos, null);
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return file;
    }
}