#!/bin/bash

set -e
set -o pipefail

/home/pi/r2cloud-jdk/bin/java -cp /home/pi/r2cloud/etc:/home/pi/r2cloud/lib/*:/usr/share/java/r2cloud/* -Djava.compiler=NONE -Duser.timezone=UTC -Dr2cloud.baseurl=https://localhost -Djava.util.logging.config.file=/home/pi/r2cloud/etc/logging-prod.properties ru.r2cloud.it.IntegrationalTests