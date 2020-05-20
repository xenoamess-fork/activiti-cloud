ALTER TABLE public.project_models DROP CONSTRAINT uk_cphei6yijnpnhdym2dckgxnka;
ALTER TABLE public.project_models ADD CONSTRAINT project_models_pk PRIMARY KEY (project_id,models_id);