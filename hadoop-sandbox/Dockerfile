FROM uhopper/hadoop:2.7.2

RUN apt update && apt install -y netselect-apt \
    && netselect-apt -c vietnam -t 2 -a amd64 -n jessie \
    && apt-get install -y openjdk-8-jdk --fix-missing \
    && rm -rf /var/lib/apt/lists/*

CMD tail -f /dev/null