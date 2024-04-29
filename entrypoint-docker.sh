# buildAndReload task, running in background, watches for source changes
(sleep 60; gradle buildAndReload --continuous -PskipDownload=true -x Test)&
gradle bootRun -PskipDownload=true