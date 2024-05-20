# Webhook Scheduler

## TODO
~~- Break apart Webhook creator and worker into 2 services~~
- run as monorepo, create run configuration with multiple services
- Retry mechanism, should the service handle it itself, or some config on RabbitMQ
~~- How do the messages get cleared? Does the worker service have to do this explicitly?~~
~~- Integrate with MongoDB~~
- Fire the webhooks: How do you test this
- Update the original DB with fired/not
  - The worker sends an update request back to the creator
- Store expired timers in a different data store
  - Persisted set, tables are heavier?
- Retry mechanism: exponential backoff
- Future: add some sort of scaling?
  - Maybe only do this in AWS
- Move config and Timer DTO to a shared package
- Dockerize the services