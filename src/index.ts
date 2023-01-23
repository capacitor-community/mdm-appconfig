import { registerPlugin } from '@capacitor/core';

import type { MDMAppConfigPlugin } from './definitions';

const AppConfig = registerPlugin<MDMAppConfigPlugin>(
  'MDMAppConfig',
  {},
);

export * from './definitions';
export { AppConfig };
