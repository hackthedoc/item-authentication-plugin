# ItemAuthentificationPlugin

ItemAuthentificationPlugin is a Minecraft plugin that allows players to identify items at a configurable cost. It integrates with Vault and an economy plugin (e.g., The New Economy) to manage payments.

Works for 1.21+ at least.
> There was no test on prior versions

## Features

- Authentificate items for a set cost using the `/authentificate` command.
- Cost can be configured in a file.
- Integrates with Vault for economy support.

## Commands

- `/authentificate` - Identifies the item in the player's main hand and authentificate it with the player's name.

## Permissions

- `itemauthentification.use` - Allows the player to use the `/authentificate` command. Enabled by default.

## Installation

1. Download the plugin jar file.
2. Place it in the `plugins` folder of your server.
3. Restart the server.
4. Configure the `config.yml` file if necessary.

### Requirements

The plugin requiers VaultUnlocked to work.

## Configuration

```yaml
identification-cost: 1 # Default cost for identifying items
