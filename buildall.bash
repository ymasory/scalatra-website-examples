# run 'sbt test' on every project

set -e

for SCRIPT in `find . -name sbt -type f -perm -u=x`
do
    PROJ=`dirname $SCRIPT`
    echo '########################################################'
    echo "BUILDING $PROJ                                          "
    echo '########################################################'
    cd $PROJ
    ./sbt test
done

