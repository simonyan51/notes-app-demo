cd notes
sh ./mvnw clean
sh ./mvnw package

cd ../users
sh ./mvnw clean
sh ./mvnw package

cd ../notes-etl
sh ./mvnw clean
sh ./mvnw package
