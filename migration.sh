#!/bin/bash

TIMESTAMP=$(date +"%Y%m%d%H%M%S")
MIGRATION_NAME="V${TIMESTAMP}__$1.sql"
MIGRATION_PATH="src/main/resources/db/migration/${MIGRATION_NAME}"

touch "$MIGRATION_PATH"
