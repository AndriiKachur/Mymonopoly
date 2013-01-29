// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package net.mymonopoly.web;

import net.mymonopoly.entity.AppRole;
import net.mymonopoly.entity.AppUser;
import net.mymonopoly.entity.AppUserRole;
import net.mymonopoly.entity.GameChance;
import net.mymonopoly.entity.GameChest;
import net.mymonopoly.entity.GameEstate;
import net.mymonopoly.entity.GameRailroad;
import net.mymonopoly.entity.GameTheme;
import net.mymonopoly.entity.GameUtility;
import net.mymonopoly.web.ApplicationConversionServiceFactoryBean;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

privileged aspect ApplicationConversionServiceFactoryBean_Roo_ConversionService {
    
    declare @type: ApplicationConversionServiceFactoryBean: @Configurable;
    
    public Converter<AppRole, String> ApplicationConversionServiceFactoryBean.getAppRoleToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<net.mymonopoly.entity.AppRole, java.lang.String>() {
            public String convert(AppRole appRole) {
                return new StringBuilder().append(appRole.getName()).append(' ').append(appRole.getDescription()).toString();
            }
        };
    }
    
    public Converter<Long, AppRole> ApplicationConversionServiceFactoryBean.getIdToAppRoleConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, net.mymonopoly.entity.AppRole>() {
            public net.mymonopoly.entity.AppRole convert(java.lang.Long id) {
                return AppRole.findAppRole(id);
            }
        };
    }
    
    public Converter<String, AppRole> ApplicationConversionServiceFactoryBean.getStringToAppRoleConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, net.mymonopoly.entity.AppRole>() {
            public net.mymonopoly.entity.AppRole convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), AppRole.class);
            }
        };
    }
    
    public Converter<AppUser, String> ApplicationConversionServiceFactoryBean.getAppUserToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<net.mymonopoly.entity.AppUser, java.lang.String>() {
            public String convert(AppUser appUser) {
                return new StringBuilder().append(appUser.getName()).append(' ').append(appUser.getSurname()).append(' ').append(appUser.getNickname()).append(' ').append(appUser.getEmail()).toString();
            }
        };
    }
    
    public Converter<Long, AppUser> ApplicationConversionServiceFactoryBean.getIdToAppUserConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, net.mymonopoly.entity.AppUser>() {
            public net.mymonopoly.entity.AppUser convert(java.lang.Long id) {
                return AppUser.findAppUser(id);
            }
        };
    }
    
    public Converter<String, AppUser> ApplicationConversionServiceFactoryBean.getStringToAppUserConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, net.mymonopoly.entity.AppUser>() {
            public net.mymonopoly.entity.AppUser convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), AppUser.class);
            }
        };
    }
    
    public Converter<AppUserRole, String> ApplicationConversionServiceFactoryBean.getAppUserRoleToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<net.mymonopoly.entity.AppUserRole, java.lang.String>() {
            public String convert(AppUserRole appUserRole) {
                return "(no displayable fields)";
            }
        };
    }
    
    public Converter<Long, AppUserRole> ApplicationConversionServiceFactoryBean.getIdToAppUserRoleConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, net.mymonopoly.entity.AppUserRole>() {
            public net.mymonopoly.entity.AppUserRole convert(java.lang.Long id) {
                return AppUserRole.findAppUserRole(id);
            }
        };
    }
    
    public Converter<String, AppUserRole> ApplicationConversionServiceFactoryBean.getStringToAppUserRoleConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, net.mymonopoly.entity.AppUserRole>() {
            public net.mymonopoly.entity.AppUserRole convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), AppUserRole.class);
            }
        };
    }
    
    public Converter<GameChance, String> ApplicationConversionServiceFactoryBean.getGameChanceToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<net.mymonopoly.entity.GameChance, java.lang.String>() {
            public String convert(GameChance gameChance) {
                return new StringBuilder().append(gameChance.getMessage()).append(' ').append(gameChance.getStopTurn()).append(' ').append(gameChance.getMoneyDiff()).append(' ').append(gameChance.getMove()).toString();
            }
        };
    }
    
    public Converter<Long, GameChance> ApplicationConversionServiceFactoryBean.getIdToGameChanceConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, net.mymonopoly.entity.GameChance>() {
            public net.mymonopoly.entity.GameChance convert(java.lang.Long id) {
                return GameChance.findGameChance(id);
            }
        };
    }
    
    public Converter<String, GameChance> ApplicationConversionServiceFactoryBean.getStringToGameChanceConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, net.mymonopoly.entity.GameChance>() {
            public net.mymonopoly.entity.GameChance convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), GameChance.class);
            }
        };
    }
    
    public Converter<GameChest, String> ApplicationConversionServiceFactoryBean.getGameChestToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<net.mymonopoly.entity.GameChest, java.lang.String>() {
            public String convert(GameChest gameChest) {
                return new StringBuilder().append(gameChest.getMessage()).append(' ').append(gameChest.getMoneyDiff()).toString();
            }
        };
    }
    
    public Converter<Long, GameChest> ApplicationConversionServiceFactoryBean.getIdToGameChestConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, net.mymonopoly.entity.GameChest>() {
            public net.mymonopoly.entity.GameChest convert(java.lang.Long id) {
                return GameChest.findGameChest(id);
            }
        };
    }
    
    public Converter<String, GameChest> ApplicationConversionServiceFactoryBean.getStringToGameChestConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, net.mymonopoly.entity.GameChest>() {
            public net.mymonopoly.entity.GameChest convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), GameChest.class);
            }
        };
    }
    
    public Converter<GameEstate, String> ApplicationConversionServiceFactoryBean.getGameEstateToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<net.mymonopoly.entity.GameEstate, java.lang.String>() {
            public String convert(GameEstate gameEstate) {
                return new StringBuilder().append(gameEstate.getUpgradeLevel()).append(' ').append(gameEstate.getColour()).append(' ').append(gameEstate.getCost()).append(' ').append(gameEstate.getRent()).toString();
            }
        };
    }
    
    public Converter<Long, GameEstate> ApplicationConversionServiceFactoryBean.getIdToGameEstateConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, net.mymonopoly.entity.GameEstate>() {
            public net.mymonopoly.entity.GameEstate convert(java.lang.Long id) {
                return GameEstate.findGameEstate(id);
            }
        };
    }
    
    public Converter<String, GameEstate> ApplicationConversionServiceFactoryBean.getStringToGameEstateConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, net.mymonopoly.entity.GameEstate>() {
            public net.mymonopoly.entity.GameEstate convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), GameEstate.class);
            }
        };
    }
    
    public Converter<GameRailroad, String> ApplicationConversionServiceFactoryBean.getGameRailroadToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<net.mymonopoly.entity.GameRailroad, java.lang.String>() {
            public String convert(GameRailroad gameRailroad) {
                return new StringBuilder().append(gameRailroad.getCost()).append(' ').append(gameRailroad.getRent1()).append(' ').append(gameRailroad.getRent2()).append(' ').append(gameRailroad.getRent3()).toString();
            }
        };
    }
    
    public Converter<Long, GameRailroad> ApplicationConversionServiceFactoryBean.getIdToGameRailroadConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, net.mymonopoly.entity.GameRailroad>() {
            public net.mymonopoly.entity.GameRailroad convert(java.lang.Long id) {
                return GameRailroad.findGameRailroad(id);
            }
        };
    }
    
    public Converter<String, GameRailroad> ApplicationConversionServiceFactoryBean.getStringToGameRailroadConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, net.mymonopoly.entity.GameRailroad>() {
            public net.mymonopoly.entity.GameRailroad convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), GameRailroad.class);
            }
        };
    }
    
    public Converter<GameTheme, String> ApplicationConversionServiceFactoryBean.getGameThemeToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<net.mymonopoly.entity.GameTheme, java.lang.String>() {
            public String convert(GameTheme gameTheme) {
                return new StringBuilder().append(gameTheme.getName()).toString();
            }
        };
    }
    
    public Converter<Long, GameTheme> ApplicationConversionServiceFactoryBean.getIdToGameThemeConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, net.mymonopoly.entity.GameTheme>() {
            public net.mymonopoly.entity.GameTheme convert(java.lang.Long id) {
                return GameTheme.findGameTheme(id);
            }
        };
    }
    
    public Converter<String, GameTheme> ApplicationConversionServiceFactoryBean.getStringToGameThemeConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, net.mymonopoly.entity.GameTheme>() {
            public net.mymonopoly.entity.GameTheme convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), GameTheme.class);
            }
        };
    }
    
    public Converter<GameUtility, String> ApplicationConversionServiceFactoryBean.getGameUtilityToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<net.mymonopoly.entity.GameUtility, java.lang.String>() {
            public String convert(GameUtility gameUtility) {
                return new StringBuilder().append(gameUtility.getCost()).append(' ').append(gameUtility.getRentMul1()).append(' ').append(gameUtility.getRentMul2()).append(' ').append(gameUtility.getMortage()).toString();
            }
        };
    }
    
    public Converter<Long, GameUtility> ApplicationConversionServiceFactoryBean.getIdToGameUtilityConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, net.mymonopoly.entity.GameUtility>() {
            public net.mymonopoly.entity.GameUtility convert(java.lang.Long id) {
                return GameUtility.findGameUtility(id);
            }
        };
    }
    
    public Converter<String, GameUtility> ApplicationConversionServiceFactoryBean.getStringToGameUtilityConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, net.mymonopoly.entity.GameUtility>() {
            public net.mymonopoly.entity.GameUtility convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), GameUtility.class);
            }
        };
    }
    
    public void ApplicationConversionServiceFactoryBean.installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getAppRoleToStringConverter());
        registry.addConverter(getIdToAppRoleConverter());
        registry.addConverter(getStringToAppRoleConverter());
        registry.addConverter(getAppUserToStringConverter());
        registry.addConverter(getIdToAppUserConverter());
        registry.addConverter(getStringToAppUserConverter());
        registry.addConverter(getAppUserRoleToStringConverter());
        registry.addConverter(getIdToAppUserRoleConverter());
        registry.addConverter(getStringToAppUserRoleConverter());
        registry.addConverter(getGameChanceToStringConverter());
        registry.addConverter(getIdToGameChanceConverter());
        registry.addConverter(getStringToGameChanceConverter());
        registry.addConverter(getGameChestToStringConverter());
        registry.addConverter(getIdToGameChestConverter());
        registry.addConverter(getStringToGameChestConverter());
        registry.addConverter(getGameEstateToStringConverter());
        registry.addConverter(getIdToGameEstateConverter());
        registry.addConverter(getStringToGameEstateConverter());
        registry.addConverter(getGameRailroadToStringConverter());
        registry.addConverter(getIdToGameRailroadConverter());
        registry.addConverter(getStringToGameRailroadConverter());
        registry.addConverter(getGameThemeToStringConverter());
        registry.addConverter(getIdToGameThemeConverter());
        registry.addConverter(getStringToGameThemeConverter());
        registry.addConverter(getGameUtilityToStringConverter());
        registry.addConverter(getIdToGameUtilityConverter());
        registry.addConverter(getStringToGameUtilityConverter());
    }
    
    public void ApplicationConversionServiceFactoryBean.afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
    
}