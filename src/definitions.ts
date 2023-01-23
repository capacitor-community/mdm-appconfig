export interface MDMAppConfigPlugin {
  /**
   * Get a value from App Config. On iOS, values are obtained from UserDefaults in com.apple.configuration.managed.
   * @param options Options to get value
   * @since 1.0.0
   */
  getValue(options: getValueOptions): Promise<GetValueResult>;
}

export interface getValueOptions {
  /**
   * The key (or variable name)
   *
   * @since 1.0.0
   */
  key: string;
}

export interface GetValueResult {
  /**
   * The value
   *
   * @since 1.0.0
   */
  value: string;
}
