#!/bin/bash

# Check if the number of arguments is correct
if [ "$#" -ne 1 ]; then
    echo "Usage: $0 <AI Name>"
    exit 1
fi

# Move to configuration directory
pushd ./src/main/java/api/ai

file="$1_1.java"

if [ -e "$file" ]; then
    filename=$(basename -- "$file")
    filename="${filename%.*}"
    copy="$filename"
    count=1
    while [ -e "$copy.java" ]; do
        copy="$(echo "$filename" | sed 's/_[0-9]*$//')_$((count++))"
    done
    cp "$file" "$copy.java"
    file="$copy.java"
    echo "Updated AI to $copy.java"
    sed -i "s/$1_\([0-9]\+\)/$1_$(expr $count - 1)/" "$copy.java"
else
    cp "DefaultController.java" "$1_1.java"
    echo "Created a new AI at $1_1.java"
    sed -i "s/DefaultController/$1_1/g" "$1_1.java"
fi


