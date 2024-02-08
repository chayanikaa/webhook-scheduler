# Webhook Scheduler

## TODO
- Break apart Webhook creator and worker into 2 services
- Integrate with MongoDB
- Fire the webhooks: How do you test this
- Update the original DB with fired/not
  - The worker sends an update request back to the creator
- Store expired timers in a different data store
  - Persisted set, tables are heavier?
- Retry mechanism: exponential backoff
- Future: add some sort of scaling?
  - Maybe only do this in AWS