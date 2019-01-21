package com.dylanthesoldier.apprepofx.model.pkg;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by c-dmoore on 10/20/2016.
 */
public class Package implements Serializable{

    private static final long serialVersionUID = -7063824719851206666L;
    public Integer UniqueID = new Random().nextInt();
	// Transient because they can't be serialized. Errors because of internal bean object.
    protected transient SimpleStringProperty Name = new SimpleStringProperty("");
    protected transient SimpleStringProperty Version = new SimpleStringProperty("");
    protected transient SimpleStringProperty PackageName = new SimpleStringProperty("");
    protected transient SimpleStringProperty PackageURL = new SimpleStringProperty("");
    protected transient SimpleStringProperty SHA_Hash = new SimpleStringProperty("");
    protected transient SimpleStringProperty SizeInBytes = new SimpleStringProperty("");
    protected transient SimpleStringProperty SilentInstall = new SimpleStringProperty("");
    protected transient SimpleStringProperty SilentUninstall = new SimpleStringProperty("");
    protected transient SimpleStringProperty Website = new SimpleStringProperty("");
    protected transient SimpleStringProperty Description = new SimpleStringProperty("");

    // --- Constructor ---
    public Package(){}

    // -- Getters ---
    public Integer getUniqueID() {
        return UniqueID;
    }

    public void setUniqueID(Integer uniqueID) {
        UniqueID = uniqueID;
    }

    public String getWebsite() {
        return Website.get();
    }

    protected void setWebsite(String website) {
        this.Website.set(website);
    }

    public SimpleStringProperty websiteProperty() {
        return Website;
    }

    public String getSilentInstall() {
        return SilentInstall.get();
    }

    protected void setSilentInstall(String silentInstall) {
        this.SilentInstall.set(silentInstall);
    }

    public SimpleStringProperty silentInstallProperty() {
        return SilentInstall;
    }

    public String getSilentUninstall() {
        return SilentUninstall.get();
    }

    protected void setSilentUninstall(String silentUninstall) {
        this.SilentUninstall.set(silentUninstall);
    }

    public SimpleStringProperty silentUninstallProperty() {
        return SilentUninstall;
    }

    public String getName() {
        return Name.get();
    }

    // --- Setters ---
    protected void setName(String name) {
        this.Name.set(name);
    }

    public SimpleStringProperty nameProperty() {
        return Name;
    }

    public String getVersion() {
        return Version.get();
    }

    protected void setVersion(String version) {
        this.Version.set(version);
    }

    public SimpleStringProperty versionProperty() {
        return Version;
    }

    public String getPackageName() {
        return PackageName.get();
    }

    protected void setPackageName(String packageName) {
        this.PackageName.set(packageName);
    }

    public SimpleStringProperty packageNameProperty() {
        return PackageName;
    }

    public String getPackageURL() {
        return PackageURL.get();
    }

    protected void setPackageURL(String packageURL) {
        this.PackageURL.set(packageURL);
    }

    public SimpleStringProperty packageURLProperty() {
        return PackageURL;
    }

    public String getSHA_Hash() {
        return SHA_Hash.get();
    }

    protected void setSHA_Hash(String SHA_Hash) {
        this.SHA_Hash.set(SHA_Hash);
    }

    public SimpleStringProperty SHA_HashProperty() {
        return SHA_Hash;
    }

    public String getSizeInBytes() {
        return SizeInBytes.get();
    }

    protected void setSizeInBytes(String sizeInBytes) {
        this.SizeInBytes.set(sizeInBytes);
    }

    public SimpleStringProperty sizeInBytesProperty() {
        return SizeInBytes;
    }

    public String getDescription() {
        return Description.get();
    }

    public void setDescription(String description) {
        this.Description.setValue(description);
    }

    public SimpleStringProperty descriptionProperty() {
        return Description;
    }

    // --- Overrides ---
    @Override public String toString() {
        return getName() + " - " + getVersion();
    }
    @Override public boolean equals(Object obj) {
        Package pkg = null;

        if (obj instanceof  Package)
            pkg = (Package) obj;
        else
            return false;

        if (UniqueID.equals(pkg.UniqueID))
            return true;

        return false;
    }
    public Package clone(){
        Package p = new Package();
        p.setName(getName());
        p.setPackageName(getPackageName());
        p.setPackageURL(getPackageURL());
        p.setSHA_Hash(getSHA_Hash());
        p.setSilentInstall(getSilentInstall());
        p.setSilentUninstall(getSilentUninstall());
        p.setSizeInBytes(getSizeInBytes());
        p.setVersion(getVersion());
        p.setWebsite(getWebsite());
        p.setUniqueID(getUniqueID());
        p.setDescription(getDescription());
        return p;
    }

    public String getPkgFileName(){
        return  getName() + "_" + getVersion() + ".pkg";
    }
}
