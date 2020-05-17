package com.rsmaxwell.userfunction;

import java.io.FileDescriptor;
import java.net.InetAddress;
import java.security.Permission;
import java.util.HashSet;
import java.util.Set;

public class MySecurityManager extends SecurityManager {

	private SecurityMode mode = SecurityMode.OPEN;

	public void setSecurityMode(SecurityMode mode) {
		this.mode = mode;
	}

	@Override
	public void checkRead(FileDescriptor filedescriptor) {
		if (mode == SecurityMode.LOCKDOWN)
			throw new SecurityException(filedescriptor.toString());
	}

	@Override
	public void checkRead(String filename) {
		super.checkRead(filename);
	}

	@Override
	public void checkRead(String filename, Object executionContext) {
		if (mode == SecurityMode.LOCKDOWN)
			throw new SecurityException(filename + ", " + executionContext.toString());
	}

	@Override
	public void checkWrite(FileDescriptor filedescriptor) {
		if (mode == SecurityMode.LOCKDOWN)
			throw new SecurityException(filedescriptor.toString());
	}

	@Override
	public void checkWrite(String filename) {
		if (mode == SecurityMode.LOCKDOWN)
			throw new SecurityException(filename);
	}

	private static Set<String> allowedPermissions = new HashSet<String>();

	static {
		allowedPermissions.add("(\"java.io.FilePermission\" \"userfunction.json\" \"read\")");
		allowedPermissions.add("(\"java.lang.RuntimePermission\" \"accessClassInPackage.sun.misc\")");
		allowedPermissions.add("(\"java.lang.RuntimePermission\" \"accessDeclaredMembers\")");
		allowedPermissions.add("(\"java.lang.RuntimePermission\" \"accessSystemModules\")");
		allowedPermissions.add(
				"(\"java.io.FilePermission\" \"C:\\Users\\Richard\\.m2\\repository\\org\\codehaus\\groovy\\groovy\\3.0.3\\groovy-3.0.3.jar\" \"read\")");
		allowedPermissions.add(
				"(\"java.io.FilePermission\" \"C:\\Users\\Richard\\.m2\\repository\\com\\google\\code\\gson\\gson\\2.8.6\\gson-2.8.6.jar\" \"read\")");
		allowedPermissions.add(
				"(\"java.io.FilePermission\" \"C:\\Users\\Richard\\git\\github.com\\rsmaxwell\\userfunction\\target\\classes\\org\\codehaus\\groovy\\runtime\\DefaultGroovyMethods$1.class\" \"read\")");
		allowedPermissions.add("(\"java.util.logging.LoggingPermission\" \"control\")");
		allowedPermissions.add("(\"java.lang.RuntimePermission\" \"shutdownHooks\")");
		allowedPermissions.add("(\"java.lang.RuntimePermission\" \"setContextClassLoader\")");
		allowedPermissions.add("(\"java.lang.RuntimePermission\" \"getProtectionDomain\")");
		allowedPermissions.add("(\"java.lang.RuntimePermission\" \"getClassLoader\")");
		allowedPermissions.add("(\"java.lang.reflect.ReflectPermission\" \"suppressAccessChecks\")");
		allowedPermissions.add(
				"(\"java.io.FilePermission\" \"C:\\Users\\Richard\\git\\github.com\\rsmaxwell\\userfunction\\target\\classes\\META-INF\\services\\org.codehaus.groovy.runtime.ExtensionModule\" \"read\")");
		allowedPermissions.add(
				"(\"java.io.FilePermission\" \"C:\\Users\\Richard\\git\\github.com\\rsmaxwell\\userfunction\\target\\classes\\META-INF\\groovy\\org.codehaus.groovy.runtime.ExtensionModule\" \"read\")");
		allowedPermissions.add("(\"java.lang.RuntimePermission\" \"createSecurityManager\")");
		allowedPermissions.add("(\"groovy.security.GroovyCodeSourcePermission\" \"/groovy/shell\")");
		allowedPermissions.add("(\"java.security.SecurityPermission\" \"getProperty.security.provider.1\")");
		allowedPermissions.add("(\"java.security.SecurityPermission\" \"getProperty.security.provider.2\")");
		allowedPermissions.add("(\"java.security.SecurityPermission\" \"getProperty.security.provider.3\")");
		allowedPermissions.add("(\"java.security.SecurityPermission\" \"getProperty.security.provider.4\")");
		allowedPermissions.add("(\"java.security.SecurityPermission\" \"getProperty.security.provider.5\")");
		allowedPermissions.add("(\"java.security.SecurityPermission\" \"getProperty.security.provider.6\")");
		allowedPermissions.add("(\"java.security.SecurityPermission\" \"getProperty.security.provider.7\")");
		allowedPermissions.add("(\"java.security.SecurityPermission\" \"getProperty.security.provider.8\")");
		allowedPermissions.add("(\"java.security.SecurityPermission\" \"getProperty.security.provider.9\")");
		allowedPermissions.add("(\"java.security.SecurityPermission\" \"getProperty.security.provider.10\")");
		allowedPermissions.add("(\"java.security.SecurityPermission\" \"getProperty.security.provider.11\")");
		allowedPermissions.add("(\"java.security.SecurityPermission\" \"getProperty.security.provider.12\")");
		allowedPermissions.add("(\"java.security.SecurityPermission\" \"getProperty.security.provider.13\")");
		allowedPermissions.add("(\"java.security.SecurityPermission\" \"getProperty.security.provider.14\")");
		allowedPermissions.add("(\"java.security.SecurityPermission\" \"getProperty.jdk.security.provider.preferred\")");
		allowedPermissions.add("(\"java.security.SecurityPermission\" \"getProperty.securerandom.source\")");
		allowedPermissions.add("(\"java.lang.RuntimePermission\" \"localeServiceProvider\")");
		allowedPermissions.add("(\"java.util.PropertyPermission\" \"jdk.security.legacyDSAKeyPairGenerator\" \"read\")");
		allowedPermissions.add("(\"java.util.PropertyPermission\" \"java.security.egd\" \"read\")");
	}

	@Override
	public void checkPermission(Permission perm) {

		if (mode == SecurityMode.LOCKDOWN) {

			String key = perm.toString();

			boolean found = allowedPermissions.contains(key);
			if (found) {
				return;
			}

			super.checkPermission(perm);
		}
	}

	@Override
	public void checkPermission(Permission perm, Object context) {
		if (mode == SecurityMode.LOCKDOWN)
			throw new SecurityException(perm.toString() + ", " + context.toString());
	}

	@Override
	public void checkCreateClassLoader() {
	}

	@Override
	public void checkAccess(Thread t) {
	}

	@Override
	public void checkAccess(ThreadGroup g) {
	}

	@Override
	public void checkExit(int status) {
		if (mode == SecurityMode.LOCKDOWN)
			throw new SecurityException(Integer.toString(status));
	}

	@Override
	public void checkExec(String cmd) {
		if (mode == SecurityMode.LOCKDOWN)
			throw new SecurityException(cmd);
	}

	@Override
	public void checkLink(String lib) {
		if (mode == SecurityMode.LOCKDOWN)
			throw new SecurityException(lib);
	}

	@Override
	public void checkDelete(String file) {
		if (mode == SecurityMode.LOCKDOWN)
			throw new SecurityException(file);
	}

	@Override
	public void checkConnect(String host, int port) {
		if (mode == SecurityMode.LOCKDOWN)
			throw new SecurityException(host + ":" + port);
	}

	@Override
	public void checkConnect(String host, int port, Object context) {
		if (mode == SecurityMode.LOCKDOWN)
			throw new SecurityException(host + ":" + port + ", context" + context.toString());
	}

	@Override
	public void checkListen(int port) {
		if (mode == SecurityMode.LOCKDOWN)
			throw new SecurityException(Integer.toString(port));
	}

	@Override
	public void checkAccept(String host, int port) {
		if (mode == SecurityMode.LOCKDOWN)
			throw new SecurityException(host + ":" + port);
	}

	@Override
	public void checkMulticast(InetAddress maddr) {
		if (mode == SecurityMode.LOCKDOWN)
			throw new SecurityException(maddr.toString());
	}

	@Override
	public void checkMulticast(InetAddress maddr, byte ttl) {
		if (mode == SecurityMode.LOCKDOWN)
			throw new SecurityException(maddr.toString() + ", ttl:" + Byte.toString(ttl));
	}

	@Override
	public void checkPropertiesAccess() {
	}

	private static Set<String> allowedProperties = new HashSet<String>();

	static {
		allowedProperties.add("groovy.use.classvalue");
		allowedProperties.add("java.util.logging.manager");
		allowedProperties.add("java.util.logging.config.class");
		allowedProperties.add("java.util.logging.config.file");
		allowedProperties.add("java.home");
		allowedProperties.add("sun.util.logging.disableCallerCheck");
		allowedProperties.add("groovy.permissive.property.access");
		allowedProperties.add("groovy.parameters");
		allowedProperties.add("groovy.preview.features");
		allowedProperties.add("file.encoding");
		allowedProperties.add("groovy.source.encoding");
		allowedProperties.add("groovy.target.directory");
		allowedProperties.add("groovy.target.bytecode");
		allowedProperties.add("groovy.default.scriptExtension");
		allowedProperties.add("groovy.target.indy");
		allowedProperties.add("groovy.attach.groovydoc");
		allowedProperties.add("groovy.attach.runtime.groovydoc");
		allowedProperties.add("groovy.antlr4");
		allowedProperties.add("groovy.antlr4.cache.threshold");
		allowedProperties.add("groovy.clear.lexer.dfa.cache");
		allowedProperties.add("groovy.antlr4.profile");
		allowedProperties.add("groovy.ast");
		allowedProperties.add("groovy.enable.parameterized.type.cache");
		allowedProperties.add("groovy.compiler.strictNames");
		allowedProperties.add("groovy.log.classgen");
	}

	@Override
	public void checkPropertyAccess(String key) {

		if (mode == SecurityMode.LOCKDOWN) {

			boolean found = allowedProperties.contains(key);
			if (found) {
				return;
			}

			super.checkPropertyAccess(key);
		}
	}

	@Override
	public void checkPrintJobAccess() {
		if (mode == SecurityMode.LOCKDOWN)
			throw new SecurityException();
	}

	@Override
	public void checkPackageAccess(String pkg) {
		super.checkPackageAccess(pkg);
	}

	@Override
	public void checkPackageDefinition(String pkg) {
		if (mode == SecurityMode.LOCKDOWN)
			throw new SecurityException(pkg);
	}

	@Override
	public void checkSetFactory() {
		if (mode == SecurityMode.LOCKDOWN)
			throw new SecurityException();
	}

	private static Set<String> allowedSecurityAccess = new HashSet<String>();

	static {
		allowedSecurityAccess.add("putProviderProperty.SUN");
	}

	@Override
	public void checkSecurityAccess(String target) {

		if (mode == SecurityMode.LOCKDOWN) {

			boolean found = allowedSecurityAccess.contains(target);
			if (found) {
				return;
			}

			super.checkSecurityAccess(target);
		}
	}

	@Override
	protected Class<?>[] getClassContext() {
		return super.getClassContext();
	}

	@Override
	public Object getSecurityContext() {
		return super.getSecurityContext();
	}

	@Override
	public ThreadGroup getThreadGroup() {
		return super.getThreadGroup();
	}

}
