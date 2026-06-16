# Skyblock

A complete, config-first Skyblock plugin for Paper/Spigot **1.21.4**. Everything a
player touches — commands, messages, menus, roles, upgrades, block values — lives
in YAML, so you can rename, retheme, or translate the whole thing without writing
a line of code. It runs fine on a single server and scales out to a multi-backend
network when you need it to.

Built for Java 21.

## Highlights

- **Real island teams with custom roles.** Ships with sensible built-in ranks
  (visitor → member → architect → moderator → owner), and owners can create their
  own roles on the fly and hand out exactly the permissions they want
  (`/is role create builder`, then `/is role perm builder block_place on`).
- **Upgrades that matter.** Team size, island size, crop doubling, mob drops,
  spawner speed, and weighted block generators — each with as many tiers as you
  care to define in `upgrades.yml`.
- **Custom generators, including obsidian.** Generators are weighted per tier, so
  high-level islands can roll ores or obsidian instead of plain cobblestone.
  Right-click obsidian with an empty bucket to pull the lava back out, so an
  obsidian farm never wastes a drop.
- **Island bank.** A shared balance per island backed by Vault. Members deposit
  from their own wallet; trusted ranks withdraw.
- **Named warps with per-rank limits.** Set as many public warps as a player's
  rank allows (`skyblock.warps.<n>`), reachable by name or through the warp menu.
- **Leaderboard + a hologram that follows you.** `/is top` prints the rankings;
  `/is top holo` spawns a personal floating leaderboard that trails the player and
  is only visible to them.
- **Per-island border colors, visitor controls, bans, and island lock.**
- **PlaceholderAPI support** out of the box (`%skyblock_level%`, `%skyblock_points%`,
  `%skyblock_bank%`, `%skyblock_flag_pvp%`, and more). Menus still render these
  placeholders even on servers without PlaceholderAPI installed.
- **MySQL or SQLite** storage, switchable in the config.
- **Cross-server ready.** An optional proxy layer keeps islands in sync across
  multiple backends behind Velocity/BungeeCord using a shared database and Redis.
- **Extensible.** A lightweight module loader lets you drop add-on jars into
  `plugins/Skyblock/modules/` and reuse the core island API.

## Commands

The command name and every alias are configurable. By default the command is
`/island` with aliases `/is`, `/sb`, `/ada`, `/sky`.

| Command | Description |
| --- | --- |
| `/is` | Open the main menu |
| `/is create [type]` | Create your island |
| `/is home` | Teleport to your island |
| `/is visit <player>` | Visit another player's island |
| `/is sethome` | Set your island spawn |
| `/is settings` | Open the settings menu (flags, lock, time) |
| `/is upgrade` | Open the upgrades menu |
| `/is border <BLUE\|GREEN\|RED>` | Change your island border color |
| `/is block` | Browse what every block is worth |
| `/is bank [deposit\|withdraw] <amount>` | Manage the island bank |
| `/is top [holo]` | Show the leaderboard, or toggle the following hologram |
| `/is setwarp <name>` / `/is delwarp <name>` | Manage public warps |
| `/is warp <player> [name]` | Warp to a public island warp |
| `/is invite\|kick\|ban\|trust\|transfer ...` | Manage your team |
| `/is role <player> <role>` | Assign a role |
| `/is role create\|delete\|perm\|list ...` | Manage custom roles |

All sub-commands support tab completion.

## Configuration

| File | What it controls |
| --- | --- |
| `config.yml` | World, command names/aliases, storage, proxy, warps, generators |
| `messages.yml` | Every player-facing message |
| `menus/*.yml` | All GUIs (main, settings, upgrades, warps, block values, help) |
| `roles.yml` | Built-in role names and permissions |
| `block-values.yml` | Points per block |
| `levels.yml` | Level thresholds |
| `upgrades.yml` | Upgrade tiers and costs |

New keys from plugin updates are merged into your existing files automatically, so
upgrading never wipes your settings.

## Dependencies

- **Paper/Spigot 1.21.4** (required)
- **Vault** + an economy plugin — optional; enables upgrade costs and the bank
- **PlaceholderAPI** — optional; adds the `%skyblock_...%` placeholders
- **FastAsyncWorldEdit** — optional; used for schematic-based island creation

## Building

```bash
./gradlew shadowJar
```

The shaded jar lands in `build/libs/`.

## License

Free to use. Pull requests and issues are welcome.
