package com.invisibleprogrammer;

import java.util.ArrayList;
import java.util.List;

class Directory {
    String name;
    Directory root;
    List<Directory> subDirectories;
    int sizeOfTheFiles;
    int size;

    public Directory(String name, Directory root) {
        this.name = name;
        this.root = root;

        this.subDirectories = new ArrayList<>();
        this.sizeOfTheFiles = 0;
        this.size = 0;
    }

    public int getSize() {
        if (this.size != 0) {
            return this.size;
        }

        this.size = sizeOfTheFiles;

        for (var subDir :
                this.subDirectories) {
            this.size += subDir.getSize();
        }


        return this.size;
    }

    public void addSubDirectory(Directory subDirectory) {
        subDirectories.add(subDirectory);
    }

    public void addFile(int size) {
        this.sizeOfTheFiles += size;
    }

    public Directory getRoot() {
        return this.root;
    }

    public List<Directory> getSubDirectories() {
        return this.subDirectories;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Directory{" +
                "name='" + name + '\'' +
                ", sizeOfTheFiles=" + sizeOfTheFiles +
                ", size=" + size +
                '}';
    }
}
