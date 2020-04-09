FROM ubuntu:18.04
RUN apt-get update
RUN apt-get install -y curl
RUN apt-get install -y gnupg2

RUN curl https://bazel.build/bazel-release.pub.gpg | apt-key add -
RUN echo "deb [arch=amd64] https://storage.googleapis.com/bazel-apt stable jdk1.8" | tee /etc/apt/sources.list.d/bazel.list
RUN apt-get update
RUN apt-get install -y bazel
