echo "********************************************************"
echo "Starting License Server"
echo "USing Profile:" $PROFILE
echo "********************************************************"
java  -jar license.jar --server.port=$SERVER_PORT
                            --spring.config.import=$SPRING_CONFIG_IMPORT
                            --spring.profiles.active=$PROFILE