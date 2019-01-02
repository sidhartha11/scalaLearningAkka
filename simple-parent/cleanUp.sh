FILES="chapter-05 \
common " 
for file in $FILES
do
echo ":$file:"
cd ./$file
pwd
rm -rf ./.classpath ./.project ./.settings ./target
cd ..
done
