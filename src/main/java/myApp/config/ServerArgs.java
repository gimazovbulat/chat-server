package myApp.config;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class ServerArgs {
    @Parameter(names = {"--port"})
    private
    Integer port;
    @Parameter(names = "--db-properties")
    private
    String properties;

    public Integer getPort() {
        return port;
    }

    public String getProperties() {
        return properties;
    }
}
