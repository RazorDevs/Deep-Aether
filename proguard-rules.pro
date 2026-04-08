-keepattributes *Annotation*,Signature,InnerClasses,Exceptions,EnclosingMethod

-keep @net.neoforged.fml.common.Mod class * { *; }

-keep @net.neoforged.fml.common.EventBusSubscriber class * { *; }

-keepclassmembers class * {
    @net.neoforged.bus.api.SubscribeEvent *;
}

-keep class io.github.razordevs.deep_aether.mixin.** { *; }

-dontwarn net.minecraft.**
-dontwarn net.neoforged.**
-dontwarn com.mojang.**
-dontwarn com.aetherteam.**
-dontwarn mezz.jei.**
-dontwarn io.wispforest.**
-dontwarn org.spongepowered.**