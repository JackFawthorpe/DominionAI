## Configuring Game Simulation

### Configuration Options

Below outlines each of the fields that are required within a configuration file

| Field             | Explanation                                                     | Example                   |
|-------------------|-----------------------------------------------------------------|---------------------------|
| statisticsEnabled | Disables the end of game statistics screen (Debugging purposes) | ```true``` or ```false``` |
| games             | The amount of games to simulate                                 | ```100```                 |
| playerCount       | The amount of players to play against                           | ```2``` to ```4```        |

### Loading Configuration file

Whenever you make changes to configuration you should run the following command at the
root directory of the project.

```./scripts/LoadConfiguration.sh <ConfigFileName>```

Alternatively, you can manually load a configuration by copying the contents of your configuration file into
```loadedConfiguration.json```. It is still recommended to have multiple configuration files as you develop
new versions of your AI strategies.

Troubleshooting:

- Windows Systems usually aren't able to run shell scripts out of the box. You will see something similar
  to the following image in this case. My recommendation is to install bash, this gives you access to linux commands
  on a Windows computer. Here's an example for Windows 10 (https://www.lifewire.com/install-bash-on-windows-10-4101773)
  ![WindowsScriptFailure.PNG](Documentation%2FWindowsScriptFailure.PNG)
