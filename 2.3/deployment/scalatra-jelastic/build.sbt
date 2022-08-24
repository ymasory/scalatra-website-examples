seq(jelasticSettings:_*)

JelasticKeys.email in JelasticKeys.deploy := sys.env.get("JELASTIC_USERNAME").getOrElse(
sys error "Please export JELASTIC_USERNAME in your shell!"
)

JelasticKeys.password in JelasticKeys.deploy := sys.env.get("JELASTIC_PWD").getOrElse(
sys error "Please export JELASTIC_PWD in your shell!"
)

JelasticKeys.apiHoster := "app.jelastic.servint.net"

JelasticKeys.environment in JelasticKeys.deploy := "scalatra-test"

publishTo := None
