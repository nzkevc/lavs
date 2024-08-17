package uoa.lavs.controllers;

/**
 * Every controller must implement this interface to be loaded by the FXMLLoader.
 *
 * <p>There are two types of controllers: pages and fragments*. Pages should only be instantiated
 * once, and have a static getSingleton() method. Fragments can be instantiated multiple times.
 *
 * <p>*Fragments are reusable components that are not pages. We originally called these components,
 * but I needed the name 'component' for the Component class.
 *
 * @see uoa.lavs.utils.objects.Component
 */
public interface IController {}
