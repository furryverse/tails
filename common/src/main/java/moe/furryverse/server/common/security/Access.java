package moe.furryverse.server.common.security;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public enum Access {
    // Cluster
    @JsonProperty("cluster.list") CLUSTER_LIST,
    @JsonProperty("cluster.get") CLUSTER_GET,
    @JsonProperty("cluster.create") CLUSTER_CREATE,
    @JsonProperty("cluster.update") CLUSTER_UPDATE,
    @JsonProperty("cluster.delete") CLUSTER_DELETE,

    // Galaxy
    @JsonProperty("galaxy.list") GALAXY_LIST,
    @JsonProperty("galaxy.get") GALAXY_GET,
    @JsonProperty("galaxy.create") GALAXY_CREATE,
    @JsonProperty("galaxy.update") GALAXY_UPDATE,
    @JsonProperty("galaxy.delete") GALAXY_DELETE,

    // Nucleus
    @JsonProperty("nucleus.list") NUCLEUS_LIST,
    @JsonProperty("nucleus.get") NUCLEUS_GET,
    @JsonProperty("nucleus.create") NUCLEUS_CREATE,
    @JsonProperty("nucleus.update") NUCLEUS_UPDATE,
    @JsonProperty("nucleus.delete") NUCLEUS_DELETE,

    // Asteroid
    @JsonProperty("asteroid.list") ASTEROID_LIST,
    @JsonProperty("asteroid.get") ASTEROID_GET,
    @JsonProperty("asteroid.create") ASTEROID_CREATE,
    @JsonProperty("asteroid.update") ASTEROID_UPDATE,
    @JsonProperty("asteroid.delete") ASTEROID_DELETE,

    // Comet
    @JsonProperty("comet.list") COMET_LIST,
    @JsonProperty("comet.get") COMET_GET,
    @JsonProperty("comet.create") COMET_CREATE,
    @JsonProperty("comet.update") COMET_UPDATE,
    @JsonProperty("comet.delete") COMET_DELETE,

    // Planet
    @JsonProperty("planet.list") PLANET_LIST,
    @JsonProperty("planet.get") PLANET_GET,
    @JsonProperty("planet.create") PLANET_CREATE,
    @JsonProperty("planet.update") PLANET_UPDATE,
    @JsonProperty("planet.delete") PLANET_DELETE,

    // Stardust
    @JsonProperty("stardust.list") STARDUST_LIST,
    @JsonProperty("stardust.get") STARDUST_GET,
    @JsonProperty("stardust.create") STARDUST_CREATE,
    @JsonProperty("stardust.update") STARDUST_UPDATE,
    @JsonProperty("stardust.delete") STARDUST_DELETE,

    // File
    @JsonProperty("file.list") FILE_LIST,
    @JsonProperty("file.get") FILE_GET,
    @JsonProperty("file.create") FILE_UPLOAD,
    @JsonProperty("file.delete") FILE_DELETE;

    public static List<Access> all() {
        return new ArrayList<>() {{
            addAll(
                    Arrays.stream(Access.values()).toList()
            );
        }};
    }

    public Access value(String access) {
        return Access.valueOf(access.toUpperCase(Locale.ROOT).replaceAll("_", "."));
    }

    @Override
    public String toString() {
        return this.name().toLowerCase(Locale.ROOT).replaceAll("_", ".");
    }

}
