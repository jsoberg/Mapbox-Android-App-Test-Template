# MapBox Android App Test Template

Test template for creating a simple PoC of a MapBox Android App.

## Setup

In order to build and run, you need to add the `MAPBOX_DOWNLOADS_TOKEN` and `MAPBOX_ACCESS_TOKEN` values to your `local.properties` files like so, before building:
```
MAPBOX_DOWNLOADS_TOKEN=my.secret.downloads.token
~~~~
MAPBOX_ACCESS_TOKEN=my.public.access.token
```

`MAPBOX_DOWNLOADS_TOKEN` is your secret token used to download dependencies from the MapBox Maven. `MAPBOX_ACCESS_TOKEN` is the public access token used during app startup. See [MapBox](https://docs.mapbox.com/android/maps/guides/install/#configure-credentials) for more details on creating these tokens.