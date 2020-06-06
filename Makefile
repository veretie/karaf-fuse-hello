.PHONY: help

help: ## Help
	@awk 'BEGIN {FS = ":.*?## "} /^[a-zA-Z_-]+:.*?## / {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}' $(MAKEFILE_LIST)

build: ## Build bundle locally
	mvn clean install

deploy: build ## Deploy bundle locally
	cp target/hellofuse*.jar ${FUSE_HOME}/deploy

broker:
	docker run -d -p 61616:61616 -p 8161:8161 --name=activemq rmohr/activemq
