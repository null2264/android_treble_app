-dontobfuscate

# okhttp probes optional tls providers at runtime; keep r8 quiet about them
-dontwarn org.bouncycastle.jsse.**
-dontwarn org.conscrypt.**
-dontwarn org.openjsse.**
