// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "CapacitorCommunityMdmAppconfig",
    platforms: [.iOS(.v14)],
    products: [
        .library(
            name: "CapacitorCommunityMdmAppconfig",
            targets: ["MDMAppConfigPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", from: "7.0.0")
    ],
    targets: [
        .target(
            name: "MDMAppConfigPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/MDMAppConfigPlugin"),
        .testTarget(
            name: "MDMAppConfigPluginTests",
            dependencies: ["MDMAppConfigPlugin"],
            path: "ios/Tests/MDMAppConfigPluginTests")
    ]
)