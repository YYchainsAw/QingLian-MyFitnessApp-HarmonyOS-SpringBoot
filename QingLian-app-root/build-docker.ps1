# build-docker.ps1

# Ensure we are in the script's directory
Set-Location $PSScriptRoot

Write-Host "1. Starting Maven Build (Skipping Tests)..." -ForegroundColor Cyan
# 使用 Maven 打包 (跳过测试)
mvn clean package -DskipTests

if ($LASTEXITCODE -ne 0) {
    Write-Host "Maven build failed!" -ForegroundColor Red
    exit 1
}

Write-Host "--------------------------------------------------------" -ForegroundColor Green
Write-Host "BUILD SUCCESS!" -ForegroundColor Green
Write-Host "Due to network issues with Docker on Windows in China, it is recommended to build the image on your server." -ForegroundColor Yellow
Write-Host "Please upload the following files to your Ubuntu server (e.g., using Baota Panel):" -ForegroundColor Cyan
Write-Host "  1. target/QingLian-app-root-1.0-SNAPSHOT.jar" -ForegroundColor White
Write-Host "  2. Dockerfile" -ForegroundColor White
Write-Host "  3. deploy-server.sh" -ForegroundColor White
Write-Host " " -ForegroundColor Cyan
Write-Host "After uploading, run this command on your server:" -ForegroundColor Cyan
Write-Host "  bash deploy-server.sh" -ForegroundColor Yellow
Write-Host "--------------------------------------------------------" -ForegroundColor Green
