CREATE TABLE [Hotel]
(
  [HotelId]             BIGINT		                                 NOT NULL IDENTITY(1,1),
  [Name]                VARCHAR(255)  COLLATE Cyrillic_General_CI_AS NOT NULL UNIQUE,
  [Address]             VARCHAR(112)  COLLATE Cyrillic_General_CI_AS NOT NULL,
  [Description]         VARCHAR(2048) COLLATE Cyrillic_General_CI_AS NOT NULL,
  [DistanceToCoastline] INT                                          NULL,
  [ImgId]               VARCHAR(255)                                 NULL,
  [Price]               INT                                          NULL,
  CONSTRAINT [Hotel_PK] PRIMARY KEY NONCLUSTERED ([HotelId] ASC)
	WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
  CONSTRAINT [Hotel_UC_Name] UNIQUE CLUSTERED ([Name] ASC)
   	WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE [Image]
(
  [ImageId]     INT		      NOT NULL IDENTITY(1,1),
  [FileName]    VARCHAR(128)  NULL,
  [Picture]     VARCHAR(MAX)  NOT NULL,
  CONSTRAINT [Image_PK] PRIMARY KEY NONCLUSTERED ([ImageId] ASC)
	WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE [Room]
(
  [RoomId]      BIGINT		                                 NOT NULL IDENTITY(1,1),
  [HotelId]     BIGINT		                                 NOT NULL,
  [RoomNumber]  INTEGER                                      NOT NULL,
  [Category]    INT                                          NULL,
  [Capacity]    INT                                          NULL,
  [Description] VARCHAR(255)  COLLATE Cyrillic_General_CI_AS NOT NULL,
  [ImgId]       INT                                          NULL,
  [Price]       INT                                          NULL,
  CONSTRAINT [Room_PK] PRIMARY KEY NONCLUSTERED ([RoomId] ASC)
	WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
  CONSTRAINT [Room_UC_RoomNumber] UNIQUE CLUSTERED ([RoomNumber] ASC)
   	WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
  CONSTRAINT Room_Hotel_FK FOREIGN KEY (HotelId) REFERENCES [Hotel](HotelId) ON DELETE CASCADE  ON UPDATE CASCADE
) ON [PRIMARY]
GO

