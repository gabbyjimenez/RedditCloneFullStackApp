set/p commit_message="Enter message: "

git add .
git commit -m "%commit_message%"
git push origin main