package competition;

/**
 * Exception to be thrown when a player fails to load
 * <p>
 * Exit code mapping:
 * <p>
 * - 03 - An error has occured but the code is bad enough that we don't know what it is
 * <p>
 * - 04 - Player 1 failed to compile
 * - 05 - Player 2 failed to compile
 * - 06 - Player 3 failed to compile
 * - 07 - Player 4 failed to compile
 * - 08 - Player 1 failed to load class
 * - 09 - Player 2 failed to load class
 * - 10 - Player 3 failed to load class
 * - 11 - Player 4 failed to load class
 */
public class CompetitionException extends RuntimeException {

    final int exitCode;

    public CompetitionException(String message, int exitCode) {
        super(message);
        this.exitCode = exitCode;
    }

    public int getExitCode() {
        return exitCode;
    }
}
