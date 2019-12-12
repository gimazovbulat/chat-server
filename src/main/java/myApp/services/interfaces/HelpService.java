package myApp.services.interfaces;

import myApp.config.Component;
import myApp.dto.HelpDto;

public interface HelpService extends Component {
    HelpDto help();
}
