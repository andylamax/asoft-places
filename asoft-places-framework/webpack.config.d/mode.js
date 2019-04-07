config.mode = "development"; // [production|development|none]
if (config.mode === "production") {
    config.optimization = {
        minimize: true
    };
}