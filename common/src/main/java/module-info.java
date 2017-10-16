module common {
    requires gson;
    requires jackson.core;
    requires jackson.databind;
    exports md.utm.fcim.common.dto;
    exports md.utm.fcim.common.enums;
    exports md.utm.fcim.common.connection;
    exports md.utm.fcim.common.connection.impl;
    exports md.utm.fcim.common.repository;
    exports md.utm.fcim.common.repository.impl;
}