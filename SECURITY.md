# Security Information

## Database Configuration

This project has been configured to use external configuration files for database credentials to maintain security best practices.

### Setup Instructions

1. **Copy the example configuration file:**
   ```bash
   cp database.properties.example database.properties
   ```

2. **Edit `database.properties` with your credentials:**
   - Never commit this file to version control
   - Keep it secure on your local machine
   - Update all placeholder values with your actual database information

3. **Alternative: Environment Variables**
   
   If the `database.properties` file is not found, the system will fall back to environment variables:
   - `DB_HOST` - Database host (default: localhost)
   - `DB_PORT` - Database port (default: 3306)
   - `DB_NAME` - Database name (default: ims)
   - `DB_USERNAME` - Database username (default: root)
   - `DB_PASSWORD` - Database password (default: empty)
   - `DB_SSL_MODE` - SSL mode (default: DISABLED)

## Important Security Notes

### For Demo/Portfolio Purposes

- This is a **demo project** intended for educational and portfolio purposes
- Any database credentials visible in git history are **no longer active**
- The original demo database has been **powered off and secured**

### For Production Use

If you plan to use this system in a production environment:

1. **Change all default passwords** immediately
2. **Enable SSL/TLS** for database connections
3. **Implement proper backup procedures**
4. **Set up user authentication** with strong password policies
5. **Enable audit logging** and monitor system access
6. **Keep software dependencies updated**
7. **Use encrypted connections** for remote database access
8. **Restrict database user permissions** to only what's needed

## Reporting Security Issues

If you discover a security vulnerability, please email the maintainer directly rather than opening a public issue.

## Best Practices

- **Never** commit `database.properties` to version control
- **Never** hardcode credentials in source code
- **Always** use secure connections (SSL/TLS) in production
- **Regularly** review and update dependencies for security patches
- **Implement** role-based access control (RBAC) properly
- **Monitor** system logs for suspicious activity

## License & Liability

This software is provided as-is for educational purposes. The developers are not responsible for any security issues arising from improper use or deployment of this system.
