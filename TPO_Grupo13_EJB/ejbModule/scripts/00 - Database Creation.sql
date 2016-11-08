USE [master]
GO

IF EXISTS(SELECT * FROM sys.databases WHERE name = 'UADE-TP-IA-2016')
BEGIN
	DROP DATABASE [UADE-TP-IA-2016]
END

/****** Object:  Database [UADE-TP-IA-2016]    Script Date: 20/09/2016 03:41:35 p.m. ******/
CREATE DATABASE [UADE-TP-IA-2016]

IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [UADE-TP-IA-2016].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO

ALTER DATABASE [UADE-TP-IA-2016] SET ANSI_NULL_DEFAULT OFF 
GO

ALTER DATABASE [UADE-TP-IA-2016] SET ANSI_NULLS OFF 
GO

ALTER DATABASE [UADE-TP-IA-2016] SET ANSI_PADDING OFF 
GO

ALTER DATABASE [UADE-TP-IA-2016] SET ANSI_WARNINGS OFF 
GO

ALTER DATABASE [UADE-TP-IA-2016] SET ARITHABORT OFF 
GO

ALTER DATABASE [UADE-TP-IA-2016] SET AUTO_CLOSE OFF 
GO

ALTER DATABASE [UADE-TP-IA-2016] SET AUTO_SHRINK OFF 
GO

ALTER DATABASE [UADE-TP-IA-2016] SET AUTO_UPDATE_STATISTICS ON 
GO

ALTER DATABASE [UADE-TP-IA-2016] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO

ALTER DATABASE [UADE-TP-IA-2016] SET CURSOR_DEFAULT  GLOBAL 
GO

ALTER DATABASE [UADE-TP-IA-2016] SET CONCAT_NULL_YIELDS_NULL OFF 
GO

ALTER DATABASE [UADE-TP-IA-2016] SET NUMERIC_ROUNDABORT OFF 
GO

ALTER DATABASE [UADE-TP-IA-2016] SET QUOTED_IDENTIFIER OFF 
GO

ALTER DATABASE [UADE-TP-IA-2016] SET RECURSIVE_TRIGGERS OFF 
GO

ALTER DATABASE [UADE-TP-IA-2016] SET  DISABLE_BROKER 
GO

ALTER DATABASE [UADE-TP-IA-2016] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO

ALTER DATABASE [UADE-TP-IA-2016] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO

ALTER DATABASE [UADE-TP-IA-2016] SET TRUSTWORTHY OFF 
GO

ALTER DATABASE [UADE-TP-IA-2016] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO

ALTER DATABASE [UADE-TP-IA-2016] SET PARAMETERIZATION SIMPLE 
GO

ALTER DATABASE [UADE-TP-IA-2016] SET READ_COMMITTED_SNAPSHOT OFF 
GO

ALTER DATABASE [UADE-TP-IA-2016] SET HONOR_BROKER_PRIORITY OFF 
GO

ALTER DATABASE [UADE-TP-IA-2016] SET RECOVERY FULL 
GO

ALTER DATABASE [UADE-TP-IA-2016] SET  MULTI_USER 
GO

ALTER DATABASE [UADE-TP-IA-2016] SET PAGE_VERIFY CHECKSUM  
GO

ALTER DATABASE [UADE-TP-IA-2016] SET DB_CHAINING OFF 
GO

ALTER DATABASE [UADE-TP-IA-2016] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO

ALTER DATABASE [UADE-TP-IA-2016] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO

ALTER DATABASE [UADE-TP-IA-2016] SET DELAYED_DURABILITY = DISABLED 
GO

ALTER DATABASE [UADE-TP-IA-2016] SET  READ_WRITE 
GO


