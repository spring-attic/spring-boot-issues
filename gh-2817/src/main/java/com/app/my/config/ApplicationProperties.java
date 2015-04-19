package com.app.my.config;

import java.io.File;
import java.net.URI;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "myApp")
public class ApplicationProperties {

    @NotNull
    private Directories directories;

    public void setDirectories(Directories directories) {
        this.directories = directories;
    }

    public Directories getDirectories() {
        return directories;
    }

    /**
     * @param path
     *            The desired path under the external directory
     * @return The file:// URI to the given path relative to the external dir.
     */
    public URI getExternalDirectoryURI(String path) {
        File file = new File(this.getDirectories().getExternal(), path);
        return file.toURI();
    }

    public static class Directories {

        @NotNull
        private String external;
        @NotNull
        private String audio;

        public String getExternal() {
            return external;
        }

        public void setExternal(String external) {
            this.external = external;
        }

        public String getAudio() {
            return audio;
        }

        public void setAudio(String audio) {
            this.audio = audio;
        }
    }

}
