import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

/**
 * @see org.springframework.boot.loader.JarLauncher#main(java.lang.String[])
 * @see org.springframework.boot.loader.Launcher#launch(java.lang.String[])
 * @see org.springframework.boot.loader.ExecutableArchiveLauncher#getClassPathArchives()
 * @see org.springframework.boot.loader.archive.JarFileArchive#getNestedArchives(org.springframework.boot.loader.archive.Archive.EntryFilter)
 * @see org.springframework.boot.loader.archive.JarFileArchive#getNestedArchive(org.springframework.boot.loader.archive.Archive.Entry)
 * @see org.springframework.boot.loader.jar.JarFile#getNestedJarFile(java.util.zip.ZipEntry)
 * @see org.springframework.boot.loader.jar.JarFile#getNestedJarFile(org.springframework.boot.loader.jar.JarEntry)
 * @see org.springframework.boot.loader.jar.JarFile#createJarFileFromEntry(org.springframework.boot.loader.jar.JarEntry)
 * @see <a href="https://github.com/spring-projects/spring-boot/issues/7096">Jar launcer adds ! after BOOT-INF/classes url for resource</a>
 * @see <a href="https://github.com/spring-projects/spring-boot/issues/7110">org.springframework.boot.loader.LaunchedURLClassLoader unable to load 3rd party FileSystemProvider implementation from spring boot executable JAR</a>
 */
public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        printClassLoaderTree(classLoader);

        // get resourceUri
        URL resourceUrl = classLoader.getResource("test.txt");
        assert resourceUrl != null;
        URI resourceUri = resourceUrl.toURI();

        // open jar if necessary
        if (resourceUri.toString().startsWith("jar:")) {
            System.out.println("\nOpening jar…");
            FileSystems.newFileSystem(resourceUri, Collections.emptyMap());
        }

        System.out.println("");

        // test files exists
        {
            Path resourcePath = Paths.get(resourceUri);
            boolean exists = Files.exists(resourcePath);
            System.out.println(
                    "Files.exists(resourcePath): " + exists
                            + "\n    resourcePath: " + resourcePath
                            + "\n    resourceUri: " + resourceUri
            );
        }

        // apply workaround
        {
            System.out.println("\nApplying workaround. Replacing '!/BOOT-INF/classes!/' with '!/BOOT-INF/classes/'.\n");
            String brokenUri = resourceUri.toString();
            String fixedUri = brokenUri.replace("!/BOOT-INF/classes!/", "!/BOOT-INF/classes/");
            resourceUri = new URI(fixedUri);
        }

        // test files exists
        {
            Path resourcePath = Paths.get(resourceUri);
            boolean exists = Files.exists(resourcePath);
            System.out.println(
                    "Files.exists(resourcePath): " + exists
                            + "\n    resourcePath: " + resourcePath
                            + "\n    resourceUri: " + resourceUri
            );
        }
    }

    private static void printClassLoaderTree(ClassLoader loader) {
        StringBuilder message = new StringBuilder();

        message.append("Using current thread context ClassLoader:\n");
        message.append("  \u25b8");
        message.append(loader.getClass().getCanonicalName());
        message.append('\n');

        ClassLoader curClassLoader = loader;
        int lvl = 0;
        while ((curClassLoader = curClassLoader.getParent()) != null) {
            int curLvl = ++lvl;
            message.append("   ");
            while (curLvl-- > 1) {
                message.append("      ");
            }
            message.append("   \u2514\u2500\u2574");
            message.append(curClassLoader.getClass().getCanonicalName());
            message.append('\n');
        }
        System.out.print(message);
    }
}
