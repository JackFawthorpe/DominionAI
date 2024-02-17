#!/bin/bash

# Check if the number of arguments is correct
if [ "$#" -ne 1 ]; then
    echo "Usage: $0 <configurationFileToLoad>"
    exit 1
fi

# Move to configuration directory
pushd ./src/main/resources/configuration

if [ -f "LoadedConfiguration.json" ]; then
    # If it exists, remove it
    rm "LoadedConfiguration.json"
    echo "Removed old configuration"
fi

if [ -f $1 ]; then
  cp $1 LoadedConfiguration.json
  echo "Loaded $1"
else
  echo "Failed to find configuration. Make sure it is placed under src/main/resources/configuration"
fi