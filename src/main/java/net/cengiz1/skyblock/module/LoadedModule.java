package net.cengiz1.skyblock.module;

import java.net.URLClassLoader;

/** Internal bookkeeping for a module that has been loaded and enabled. */
final class LoadedModule {

    private final ModuleDescriptor descriptor;
    private final SkyblockModule instance;
    private final ModuleContext context;
    private final URLClassLoader classLoader;

    LoadedModule(ModuleDescriptor descriptor, SkyblockModule instance,
                 ModuleContext context, URLClassLoader classLoader) {
        this.descriptor = descriptor;
        this.instance = instance;
        this.context = context;
        this.classLoader = classLoader;
    }

    ModuleDescriptor getDescriptor() {
        return descriptor;
    }

    SkyblockModule getInstance() {
        return instance;
    }

    ModuleContext getContext() {
        return context;
    }

    URLClassLoader getClassLoader() {
        return classLoader;
    }
}
