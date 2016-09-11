/*
package de.marhan.aether;

import com.jcabi.aether.Aether;
import org.sonatype.aether.artifact.Artifact;
import org.sonatype.aether.repository.RemoteRepository;
import org.sonatype.aether.util.artifact.DefaultArtifact;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

public class Downloader {


    public static void main(String[] ags) {
        try {
            File local = new File("build/");
            Collection<RemoteRepository> remotes = Arrays.asList(
                    new RemoteRepository(
                            "maven-central",
                            "default",
                            "http://repo1.maven.org/maven2/"
                    )
            );
            Collection<Artifact> deps = new Aether(remotes, local).resolve(
                    new DefaultArtifact("junit", "junit-dep", "", "jar", "4.10"),
                    "runtime"
            );

            System.out.println("Got the Junit dependencies");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
*/
